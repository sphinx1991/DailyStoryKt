package com.sphinx.dailystorykt.ui.info

import android.os.Bundle
import android.view.MenuItem
import com.sphinx.dailystorykt.BR
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.ActivityInfoBinding
import com.sphinx.dailystorykt.ui.base.BaseActivity
import javax.inject.Inject



/**
 * Created by sphinx on 06/08/2019
 */
class InfoActivity : BaseActivity<ActivityInfoBinding, InfoViewModel>(), InfoView {

    @Inject
    private lateinit var mInfoViewModel: InfoViewModel
    private lateinit var mBinding: ActivityInfoBinding

    private val articleModel: NewsResponseModel.ArticleModel by lazy {
        intent.getSerializableExtra("article") as NewsResponseModel.ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        mBinding.apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            }
        }
        mInfoViewModel.setData(articleModel)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = com.sphinx.dailystorykt.R.layout.activity_info

    override fun getBindingVariable() = BR.infoViewModel

    override fun getViewModel(): InfoViewModel = mInfoViewModel
}