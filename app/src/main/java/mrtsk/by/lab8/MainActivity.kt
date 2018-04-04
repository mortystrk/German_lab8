package mrtsk.by.lab8

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import mrtsk.by.lab8.country.Country
import mrtsk.by.lab8.fragments.CountriesFragment

class MainActivity : AppCompatActivity(), CountriesFragment.OnFragmentCallbackInterface {
    override fun onFragmentCallback(string: String) {

    }

    private val countries = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillCountriesArrayList()

        val fragment = CountriesFragment.newInstance(countries)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val text = findViewById<TextView>(R.id.tv_countries_frame_info)
        text.visibility = View.INVISIBLE
        fragmentTransaction.add(R.id.frame_countries_container, fragment)
        fragmentTransaction.commit()
    }

    private fun fillCountriesArrayList() {
        countries.add(Country(resources.getString(R.string.australia), R.drawable.australia, resources.getStringArray(R.array.australia_cities)))
        countries.add(Country(resources.getString(R.string.belarus), R.drawable.belarus, resources.getStringArray(R.array.belarus_cities)))
        countries.add(Country(resources.getString(R.string.canada), R.drawable.canada, resources.getStringArray(R.array.canada_cities)))
        countries.add(Country(resources.getString(R.string.denmark), R.drawable.denmark, resources.getStringArray(R.array.denmark_cities)))
        countries.add(Country(resources.getString(R.string.germany), R.drawable.germany, resources.getStringArray(R.array.germany_cities)))
        countries.add(Country(resources.getString(R.string.hungary), R.drawable.hungary, resources.getStringArray(R.array.hungary_cities)))
        countries.add(Country(resources.getString(R.string.niue), R.drawable.niue, resources.getStringArray(R.array.niue_cities)))
    }
}
