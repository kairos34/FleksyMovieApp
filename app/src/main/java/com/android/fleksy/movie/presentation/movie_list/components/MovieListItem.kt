package com.android.fleksy.movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.android.fleksy.movie.R
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.common.MarqueeText
import com.android.fleksy.movie.presentation.theme.LightGray
import com.android.fleksy.movie.util.safeClick
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                safeClick {
                    onItemClick(movie)
                }
            }
            .padding(
                bottom = 5.dp,
                top = 5.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .size(128.dp)
                    .clip(RoundedCornerShape(10))) {
                CoilImage(
                    imageModel = "${Constants.SMALL_IMAGE_URL}${movie.posterPath}",
                    imageLoader = {
                        ImageLoader.Builder(LocalContext.current)
                            .availableMemoryPercentage(0.25)
                            .build()
                    },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = LightGray,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    error = ImageBitmap.imageResource(R.drawable.placeholder)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    MarqueeText(
                        text = movie.name,
                        style = MaterialTheme.typography.h3.copy(
                            color = MaterialTheme.colors.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Release: ${movie.date}",
                        modifier = Modifier.padding(vertical = 4.dp),
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 12.sp
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "Rating  •  ${movie.vote}/10",
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.padding(vertical = 4.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}