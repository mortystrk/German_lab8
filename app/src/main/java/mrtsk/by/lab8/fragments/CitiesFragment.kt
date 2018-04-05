package mrtsk.by.lab8.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.city_list_fragment.*
import mrtsk.by.lab8.R

class CitiesFragment : Fragment() {

    private lateinit var cities: Array<String>
    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            cities = arguments.getStringArray(ARG_CITIES_LIST)

            citiesAdapter = CitiesAdapter(cities)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (inflater != null) {
            inflater.inflate(R.layout.city_list_fragment, container, false)
        } else {
            val textView = TextView(activity)
            textView.setText(R.string.inflater_is_null)
            textView
        }
    }

    override fun onResume() {
        super.onResume()
        listV_cities.adapter = citiesAdapter
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.list_layout_controller)
        listV_cities.layoutAnimation = controller
    }

    companion object {
        private val ARG_CITIES_LIST = "cities"

        fun newInstance(param1: Array<String>) : CitiesFragment {
            val fragment = CitiesFragment()
            val args = Bundle()
            args.putStringArray(ARG_CITIES_LIST, param1)
            fragment.arguments = args
            return fragment
        }
    }

    inner class CitiesAdapter(private var citiesList: Array<String>) : BaseAdapter() {

        @SuppressLint("NewApi")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.city_item, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            viewHolder.cityName.text = citiesList[position]

            return view
        }

        override fun getItem(position: Int): Any {
            return citiesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return citiesList.size
        }
    }

    private class ViewHolder(view: View?) {
        val cityName: TextView = view?.findViewById<TextView>(R.id.tv_city_name) as TextView
    }
}