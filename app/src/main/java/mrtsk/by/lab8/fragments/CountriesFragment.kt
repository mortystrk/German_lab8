package mrtsk.by.lab8.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.country_list_fragment.*
import mrtsk.by.lab8.R
import mrtsk.by.lab8.country.Country


class CountriesFragment : Fragment() {

    private var countries = ArrayList<Country>()
    private lateinit var countriesAdapter: CountriesAdapter

    private var mCallback: OnFragmentCallbackInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            countries = arguments.getParcelableArrayList(ARG_COUNTRIES_LIST)

            countriesAdapter = CountriesAdapter(countries)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (inflater != null) {
            inflater.inflate(R.layout.country_list_fragment, container, false)
        } else {
            val textView = TextView(activity)
            textView.setText(R.string.inflater_is_null)
            textView
        }
    }

    override fun onResume() {
        super.onResume()
        listV_countries.adapter = countriesAdapter
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentCallbackInterface) {
            mCallback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentCallbackInterface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
    }

    interface OnFragmentCallbackInterface {
        fun onFragmentCallback(string: String)
    }

    companion object {
        private val ARG_COUNTRIES_LIST = "countries"

        fun newInstance(param1: ArrayList<Country>) : CountriesFragment {
            val fragment = CountriesFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_COUNTRIES_LIST, param1)
            fragment.arguments = args
            return fragment
        }
    }

    inner class CountriesAdapter(private var countriesList: ArrayList<Country>) : BaseAdapter() {

        @SuppressLint("NewApi")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.country_item, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            viewHolder.countryName.text = countriesList[position].name
            viewHolder.countryFlag.setImageResource(countriesList[position].flag)

            return view
        }

        override fun getItem(position: Int): Any {
            return countriesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return countriesList.size
        }
    }

    private class ViewHolder(view: View?) {
        val countryName: TextView = view?.findViewById<TextView>(R.id.tv_country_name) as TextView
        val countryFlag: ImageView = view?.findViewById<ImageView>(R.id.imageV_country_flag) as ImageView
    }
}