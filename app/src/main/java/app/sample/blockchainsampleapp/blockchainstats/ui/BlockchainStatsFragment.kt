package app.sample.blockchainsampleapp.blockchainstats.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import app.sample.blockchainsampleapp.R
import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource
import app.sample.blockchainsampleapp.blockchainstats.response.ChartPoint
import app.sample.blockchainsampleapp.blockchainstats.viewmodel.BlockchainStatsViewModel
import app.sample.blockchainsampleapp.blockchainstats.viewmodel.ViewModelFactory
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.TooltipPositionMode
import de.n26.n26blockchain.base.common.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_blockchainstats.*
import java.net.UnknownHostException
import java.util.concurrent.CopyOnWriteArrayList


class BlockchainStatsFragment : BaseFragment() {

    val TAG = "BlockchainStatsFragment"

    var blockChainStatsViewModel: BlockchainStatsViewModel =
        ViewModelFactory.getInstance().create(BlockchainStatsViewModel::class.java)
    var rootView: View? = null
    override fun getLayoutRes(): Int {
        return R.layout.fragment_blockchainstats
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutRes(), container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadViewData()
    }


    private fun showProgressBar() {
        rootView!!.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        rootView!!.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }

    override fun showErrorView(throwable: Throwable) {
        Log.e(TAG, "Something wrong happened", throwable)
        anyChartView.visibility = View.GONE
        specialErrorView.visibility = View.VISIBLE
        if (throwable is UnknownHostException) {
            // No internet connection
            specialErrorView.subtitle = getString(R.string.msg_error_no_connection)
        } else {
            specialErrorView.subtitle = getString(R.string.msg_error_general)
        }
        specialErrorView.setRetryListener {
            loadViewData()
        }
    }

    override fun hideErrorView() {
        specialErrorView.visibility = View.GONE
    }

    override fun loadViewData() {
        showProgressBar()
        hideErrorView()
        blockChainStatsViewModel.fetchBlockchainStats(object : BlockchainStatsDataSource.FetchStatsCallback {
            override fun onStatsFetched(chartPoints: List<ChartPoint>) {
                val seriesData = CopyOnWriteArrayList<DataEntry>()
                val chartPointsCon = CopyOnWriteArrayList(chartPoints)
                val iterator = chartPointsCon.iterator()
                while (iterator.hasNext()) {
                    val chartPoint = iterator.next()
                    val x = chartPoint.x.toFloatOrNull()
                    val y = chartPoint.y.toFloatOrNull()
                    if (x != null && y != null) {
                        val valueDataEntry = ValueDataEntry(x, y)
                        seriesData.add(valueDataEntry)
                    }
                }
                val cartesian = AnyChart.line()
                cartesian.animation(true)

                cartesian.padding(5.0, 5.0, 5.0, 5.0)

                cartesian.crosshair().enabled(true)
                cartesian.crosshair()
                    .yLabel(true)
                cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

                cartesian.title(getString(R.string.chart_description))

                cartesian.yAxis(0).title(getString(R.string.chart_title))
                cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

                cartesian.removeAllSeries()
                cartesian.data(seriesData)
                hideErrorView()
                anyChartView.visibility = View.VISIBLE
                anyChartView.setChart(cartesian)
                anyChartView.invalidate()
                hideProgressBar()
            }

            override fun onStatsNotAvailable(throwable: Throwable) {
                hideProgressBar()
                showErrorView(throwable)
            }
        })
    }

}