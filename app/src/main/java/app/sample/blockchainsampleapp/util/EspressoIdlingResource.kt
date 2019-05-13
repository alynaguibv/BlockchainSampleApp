package app.sample.blockchainsampleapp.util


/**
 * Contains a static reference to [IdlingResource], only available in the 'mock' build type.
 */
object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}
