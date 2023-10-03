package com.example.alarmmanager.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarmmanager.MyReceiver
import com.example.alarmmanager.databinding.FragmentMainBinding
import java.util.Calendar


class MainFragment : Fragment() {
    private val calendar = Calendar.getInstance()
    private lateinit var alarmIntent: PendingIntent
    private var alarmManager: AlarmManager? = null
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timePicker.setIs24HourView(true)
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, MyReceiver::class.java).let { intent ->
            intent.putExtra("key", "HELLO")
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        binding.btStart.setOnClickListener {
            calendar.set(Calendar.HOUR_OF_DAY, binding.timePicker.hour)
            calendar.set(Calendar.MINUTE, binding.timePicker.minute)
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmIntent,

                )
        }

        binding.btRepeat.setOnClickListener {
            alarmManager?.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime(),
                60 * 1000,
                alarmIntent
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
