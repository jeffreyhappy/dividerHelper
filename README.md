# dividerHelper
在android RecyclerView中添加分割线的帮助类

####直接使用颜色
```
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = TextAdapter()
        val divider:RecyclerView.ItemDecoration = DividerHelper.Builder()
                .divider(Color.RED)
                .height(10)
                .width(10)
                .marginLeft(50)
                .marginRight(50)
                .build()
        rv.addItemDecoration(divider)
```
####使用drawable
```
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = TextAdapter()
        val divider: RecyclerView.ItemDecoration =
                DividerHelper.Builder()
                        .divider(ActivityCompat.getDrawable(activity, R.drawable.shape_divider_brand_left_padding))
                        .marginLeft(100)
                        .marginRight(100)
                        .build()
        rv.addItemDecoration(divider)
```

####复杂的分割线的话 需要自己实现DrawableProvider
```
rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = TextAdapter()

        val divider: RecyclerView.ItemDecoration =
                DividerHelper.Builder()
                        .divider { position, recyclerView ->
                            if (position % 2 == 0) {
                                ColorDrawable(Color.RED)
                            } else {
                                ActivityCompat.getDrawable(activity, R.drawable.shape_divider_brand_left_padding)
                            }
                        }
                        .marginLeft(100)
                        .marginRight(100)
                        .build()
        rv.addItemDecoration(divider)

```


