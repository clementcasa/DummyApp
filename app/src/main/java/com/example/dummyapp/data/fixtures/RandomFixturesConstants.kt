package com.example.dummyapp.data.fixtures

import kotlin.random.Random

class RandomFixturesConstants {
    
    companion object {
        fun getRandomFirstName() =
            listOf("Tony", "Steve", "Bruce", "Natascha", "Carol")[(0..4).random()]
        fun getRandomLastName() =
            listOf("Stark", "Rogers", "Banner", "Romanoff", "Danvers")[(0..4).random()]
        fun getRandomUserTitle() =
            listOf(
                "mr",
                "ms",
                "mrs",
                "miss",
                "dr",
                ""
            )[(0..5).random()]
        fun getRandomUserGender() =
            listOf(
                "male",
                "female",
                "other",
                "",
            )[(0..3).random()]
        fun getRandomPhoto() =
            listOf(
                "https://i.guim.co.uk/img/media/26392d05302e02f7bf4eb143bb84c8097d09144b/446_167_3683_2210/master/3683.jpg?width=620&quality=45&auto=format&fit=max&dpr=2&s=6fe0ebd22151102996062fa1287f824c",
                "https://desenio.fr/bilder/artiklar/zoom/8684_2.jpg",
                "https://ps.w.org/primary-cat/assets/icon-256x256.jpg",
                "https://is5-ssl.mzstatic.com/image/thumb/Purple125/v4/27/3e/30/273e3017-c801-8b5a-854c-c730bdc9349a/source/256x256bb.jpg"
            )[(0..3).random()]
    }
}