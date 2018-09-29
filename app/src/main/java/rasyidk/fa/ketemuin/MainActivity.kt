package rasyidk.fa.ketemuin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RewardedVideoAdListener {

    private lateinit var rewardedVideoAd: RewardedVideoAd
    private lateinit var mInterstitialAd: InterstitialAd
    private val adRequest = AdRequest.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-1394596689730391~4054980377")
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this@MainActivity)
        rewardedVideoAd.rewardedVideoAdListener = this

        mInterstitialAd = InterstitialAd(this@MainActivity)
        mInterstitialAd.adUnitId = "ca-app-pub-1394596689730391/4619879160"

        mAdView.loadAd(adRequest)
        loadRewardedVideoAd()
        loadRewardedPamfletAd()

        btnVideo.setOnClickListener {
            if (rewardedVideoAd.isLoaded) {
                rewardedVideoAd.show()
            }
        }

        btnPamflet.setOnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        rewardedVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        rewardedVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        rewardedVideoAd.destroy(this)
    }

    private fun loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-1394596689730391/8404740181", adRequest)
    }

    private fun loadRewardedPamfletAd() {
        mInterstitialAd.loadAd(adRequest)
    }

    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
                Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
    }
}
