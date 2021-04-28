package com.example.animeapplication

object Constants {

    const val USER_NAME = "userName"
    const val CORRECT_CHOSEN = "correctChosen"
    const val TOTAL_QUESTION = "totalQuestion"

    fun getQuestionList(): ArrayList<AnimeQuestions>{
        val questionList = ArrayList<AnimeQuestions>()

        val que1 = AnimeQuestions(1, "What is the name of the Anime?", R.drawable.ic_onepiece, "Naruto", "World Trigger", "One Piece", "Inazuma 11", 3)
        questionList.add(que1)

        val que2 = AnimeQuestions(2, "What is the name of the Anime?", R.drawable.ic_naruto, "Naruto", "World Trigger", "Gintama", "Detective Conan", 1)
        questionList.add(que2)

        val que3 = AnimeQuestions(3, "Name the Character", R.drawable.kuga_yuuma, "Uzumaki Ussop", "Yuuma Kuga", "Osamu Mikumo", "Shuichi Akai", 2)
        questionList.add(que3)

        val que4 = AnimeQuestions(4, "What is the name of the Anime?", R.drawable.bungyou, "Katekyo Hitman Reborn!", "Violet Evergarden", "Vinland Saga", "Bungo Stray Dogs", 4)
        questionList.add(que4)

        return questionList
    }
}