package com.android.fleksy.movie.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.android.fleksy.movie.R
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.common.MarqueeText
import com.android.fleksy.movie.presentation.theme.ColorPrimary
import com.android.fleksy.movie.presentation.theme.MediumGray
import com.android.fleksy.movie.util.safeClick

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                safeClick {
                    onItemClick(movie)
                }
            }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val painter = if(movie.posterPath.isNullOrEmpty()) {
            painterResource(id = R.drawable.placeholder)
        } else {
            rememberImagePainter(
                data = "${Constants.SMALL_IMAGE_URL}${movie.posterPath}",
                builder = {
                    placeholder(R.drawable.placeholder)
                    fallback(R.drawable.placeholder)
                }
            )
        }
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(10))
                .border(5.dp, MediumGray, RoundedCornerShape(10))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {
                MarqueeText(
                    text = movie.name,
                    style = MaterialTheme.typography.h3.copy(
                        color = ColorPrimary
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