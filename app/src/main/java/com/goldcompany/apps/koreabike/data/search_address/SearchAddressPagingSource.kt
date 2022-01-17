package com.goldcompany.apps.koreabike.data.search_address

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.goldcompany.apps.koreabike.api.KakaoApiService
import retrofit2.HttpException
import java.io.IOException

class SearchAddressPagingSource(
    private val service: KakaoApiService,
    private val address: String
) : PagingSource<Int, AddressItem>() {
    private val KAKAO_API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AddressItem> {
        val position = params.key ?: 1
        return try {
            val response = service.searchAddress(KAKAO_API_KEY, address = address, position)
            val addresses = response.addressList
            LoadResult.Page(
                data = addresses,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.meta.isEnd) null else position + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AddressItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}