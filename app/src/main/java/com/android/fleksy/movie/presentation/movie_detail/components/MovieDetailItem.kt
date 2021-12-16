package com.android.fleksy.movie.presentation.movie_detail.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.presentation.theme.ColorPrimary
import com.android.fleksy.movie.presentation.theme.MediumGray

@Composable
fun MovieDetailItem(movie: Movie, posterHeight: Dp) {
    Column {
        Image(
            painter = rememberImagePainter(data = "${Constants.LARGE_IMAGE_URL}${movie.posterPath}"),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(posterHeight)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.name,
                    modifier = Modifier.padding(8.dp),
                    style = typography.h6.copy(
                        color = ColorPrimary,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "Release: ${movie.date}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Rating  •  ${("%.1f".format(movie.vote)).replace(',', '.')}/10",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                )
                Text(
                    text = "Overview",
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    style = typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorPrimary
                    )
                )
                Card(
                    border = BorderStroke(2.dp, MediumGray),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = MaterialTheme.colors.onBackground,
                    contentColor = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (movie.overview.isNotEmpty()) movie.overview else "Overview not found!",
                        color = if (movie.overview.isNotEmpty()) Color.Unspecified else Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .verticalScroll(rememberScrollState(0)),
                        style = typography.subtitle2.copy(fontSize = 12.sp)
                    )
                }
            }
        }
    }
}