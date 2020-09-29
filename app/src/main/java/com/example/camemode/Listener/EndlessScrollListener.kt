package com.example.camemode.Listener

import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener: RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!recyclerView.canScrollVertically(-1)) {
            // リストの先頭の場合
            android.util.Log.d("EndlessScrollListener", "onScrolled Top")
        }

        if (!recyclerView.canScrollVertically(1)) {
            // リストの末尾の場合
            android.util.Log.d("EndlessScrollListener", "onScrolled Bottom")
        }
    }
}