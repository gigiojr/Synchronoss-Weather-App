package com.example.syncronossweatherapp.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.syncronossweatherapp.R
import com.example.syncronossweatherapp.WeatherApplication
import com.example.syncronossweatherapp.di.ViewModelFactory
import com.example.syncronossweatherapp.model.WeatherResponse
import com.squareup.picasso.Picasso
import javax.inject.Inject
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.label_value_component.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private val loadingObserver = Observer<Boolean> { setProgressBar(it) }
    private val weatherObserver = Observer<WeatherResponse?> { onWeatherUpdate(it) }
    private val workObserver = Observer<WorkInfo> { viewModel.setWorkStatus(it.state) }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                initWorker()
            } else { // Do something as the permission is not granted
                Log.d("LOG_TAG", "permission denied by the user")
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireContext().applicationContext as WeatherApplication).appComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.isLoading.observe(viewLifecycleOwner, loadingObserver)
        viewModel.weather.observe(viewLifecycleOwner, weatherObserver)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        viewModel.updateData()

        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {

            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            initWorker()
        }
    }

    private fun initLayout() {
        temp.label.text = getString(R.string.main_fragment_temp_label)
        tempFeel.label.text = getString(R.string.main_fragment_temp_feel_label)
        tempMax.label.text = getString(R.string.main_fragment_temp_max_label)
        tempMin.label.text = getString(R.string.main_fragment_temp_min_label)
        pressure.label.text = getString(R.string.main_fragment_pressure_label)
        humidity.label.text = getString(R.string.main_fragment_humidity_label)
        windSpeed.label.text = getString(R.string.main_fragment_wind_speed_label)

        setProgressBar(false)
    }

    private fun initWorker() {
        context?.let {
            val workManager = WorkManager.getInstance(it)
            val workId = viewModel.startWorker(workManager)

            workManager
                .getWorkInfoByIdLiveData(workId)
                .observe(viewLifecycleOwner, workObserver)
        }
    }

    private fun setImage(url: String? = "https://gdf.coth.com/images/default_image.jpg") {
        Picasso
            .get()
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    private fun setProgressBar(show: Boolean?) {
        progressBar.visibility = if (show == true) View.VISIBLE else View.GONE
    }

    private fun onWeatherUpdate(weather: WeatherResponse?) {
        weather?.let {
            if (it.weather.isNotEmpty()) {
                val w = it.weather[0]
                setImage(String.format(getString(R.string.url_openweathermap_icon), w.icon))
                mainLabel.text = String.format(getString(R.string.main_fragment_main_value),
                    w.main, w.description)
            } else {
                setImage()
                mainLabel.text = ""
            }

            cityLabel.text = it.name
            temp.value.text = String.format(getString(R.string.main_fragment_temp_value), it.main.temp)
            tempFeel.value.text = String.format(getString(R.string.main_fragment_temp_value), it.main.feels_like)
            tempMax.value.text = String.format(getString(R.string.main_fragment_temp_value), it.main.temp_max)
            tempMin.value.text = String.format(getString(R.string.main_fragment_temp_value), it.main.temp_min)
            pressure.value.text = String.format(getString(R.string.main_fragment_pressure_value), it.main.pressure)
            humidity.value.text = String.format(getString(R.string.main_fragment_humidity_value), it.main.humidity)
            windSpeed.value.text = String.format(getString(R.string.main_fragment_wind_speed_value), it.wind.speed)
        } ?: run {
            setImage()
            cityLabel.text = ""
            mainLabel.text = ""
            temp.value.text = ""
            tempFeel.value.text = ""
            tempMax.value.text = ""
            tempMin.value.text = ""
            pressure.value.text = ""
            humidity.value.text = ""
            windSpeed.value.text = ""
        }
    }
}