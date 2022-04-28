package com.example.weatherkotlin.ui.main.threads

import android.content.*
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.weatherkotlin.R
import com.example.weatherkotlin.databinding.FragmentThreadsBinding
import com.example.weatherkotlin.services.BoundService
import com.example.weatherkotlin.services.ForegroundService
import java.util.*

class ThreadsFragment : Fragment() {
    private var _binding: FragmentThreadsBinding? = null
    private val binding get() = _binding!!

    private var isBound = false
    private var boundService: BoundService.ServiceBinder? = null

    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(
                context,
                "FROM SERVICE: ${intent?.getBooleanExtra(ForegroundService.INTENT_SERVICE_DATA, false)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Обработка соединения с сервисом
    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        // При соединении с сервисом
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service as BoundService.ServiceBinder
            isBound = boundService != null
            Log.i("SERVICE", "BOUND SERVICE")
            Log.i("SERVICE", "next fibonacci: ${service.nextFibonacci}")
        }

        // При разрыве соединения с сервисом
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val handler = Handler(Looper.getMainLooper())
            val thread = Thread {
                val result = startCalculations(binding.editText.text.toString().toInt())
                handler.post {
                    binding.textView.text = result
                    binding.mainContainer.addView(TextView(it.context).apply {
                        text = getString(R.string.in_main_thread)
                        textSize = resources.getDimension(R.dimen.main_container_text_size)
                    })
                }
                /*binding.textView.post {
                    binding.textView.text = result
                    binding.mainContainer.addView(TextView(it.context).apply {
                        text = getString(R.string.in_main_thread)
                        textSize = resources.getDimension(R.dimen.main_container_text_size)
                    })
                }*/
                /*activity?.runOnUiThread {
                    binding.textView.text = result
                        binding.mainContainer.addView(TextView(it.context).apply {
                            text = getString(R.string.in_main_thread)
                            textSize = resources.getDimension(R.dimen.main_container_text_size)
                        })
                }*/
            }
            thread.start()

            /*val handlerThread = HandlerThread(getString(R.string.my_handler_thread))
            handlerThread.start()
            val handlerNew = Handler(handlerThread.looper)
            handlerNew.post {
                //наш код
            }
            handlerThread.quitSafely()*/
        }

        ForegroundService.start(requireContext())
    }

    override fun onStart() {
        super.onStart()
        if (!isBound) {
            val bindServiceIntent = Intent(context, BoundService::class.java)
            activity?.bindService(bindServiceIntent, boundServiceConnection, Context.BIND_AUTO_CREATE)
        }
        activity?.registerReceiver(testReceiver, IntentFilter(ForegroundService.INTENT_ACTION_KEY))
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        activity?.unregisterReceiver(testReceiver)
    }

    private fun startCalculations(seconds: Int): String {
        val date = Date()
        var diffInSec: Long
        do {
            val currentDate = Date()
            val diffInMs: Long = currentDate.time - date.time
            diffInSec = diffInMs / 1000L
        } while (diffInSec < seconds)
        return diffInSec.toString()
    }

    companion object {
        fun newInstance() = ThreadsFragment()
    }
}