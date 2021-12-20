package com.android.fleksy.movie.data

import com.android.fleksy.movie.domain.model.Movie

val firstMovie = lazy {
    Movie(
        id = 130392,
        name = "The D'Amelio Show",
        vote = 9.4,
        posterPath = "/z0iCS5Znx7TeRwlYSd4c01Z0lFx.jpg",
        overview = "From relative obscurity and a seemingly normal life, " +
                "to overnight success and thrust into the Hollywood " +
                "limelight overnight, the D’Amelios are faced with " +
                "new challenges and opportunities they could not have imagined.",
        date = "2021-09-03"
    )
}

val lastMovieInFirstPage = lazy {
    Movie(
        id = 83121,
        name = "Kaguya-sama: Love is War",
        vote = 8.9,
        posterPath = "/5khbC6AuNgnvnoDbjIMKCOhEtIc.jpg",
        overview = "Considered a genius due to having " +
                "the highest grades in the country, " +
                "Miyuki Shirogane leads the prestigious " +
                "Shuchiin Academy's student council " +
                "as its president, working alongside " +
                "the beautiful and wealthy vice president " +
                "Kaguya Shinomiya. The two are often regarded " +
                "as the perfect couple by students despite them not " +
                "being in any sort of romantic relationship.",
        date = "2019-01-12"
    )
}

val firstSimilarMovieWithTopRated = lazy {
    Movie(
        id = 1508,
        name = "Dancing with the Stars",
        vote = 5.5,
        posterPath = "/bzBUukgTAFz8qTmZPvt8NajFx2M.jpg",
        overview = "The competition sees celebrities perform choreographed dance routines " +
                "which are judged by a panel of renowned ballroom experts and voted on by " +
                "viewers. Enjoy sizzling salsas, sambas and spray-tans as they vie for " +
                "the coveted Mirrorball Trophy.",
        date = "2005-06-01"
    )
}

val lastSimilarMovieWithTopRated = lazy {
    Movie(
        id = 69299,
        name = "The House",
        vote = 7.0,
        posterPath = "/cdMs2scw4et2FQCKwRdnpplb8IK.jpg",
        overview = "Every episode, one interesting Belgian visits \"The House\" (\"Het huis\") " +
                "for 24 hours. In this imposing house with a pool, surrounded by " +
                "a forest, in an unknown location, the visitor is \"locked away\" " +
                "from the rest of the world for 24 hours. They are interviewed, " +
                "dissected, confronted by the interviewer.",
        date = "2015-10-20"
    )
}