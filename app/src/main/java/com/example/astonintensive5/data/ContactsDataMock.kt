package com.example.astonintensive5.data

import com.example.astonintensive5.model.Contact

object ContactsDataMock {
    fun getStartData(): ArrayList<Contact> {
        return arrayListOf(
            Contact(
                id = 0,
                name = "Саша",
                surname = "Иванов",
                number = "+7 (900) 245 04 05"
            ),
            Contact(
                id = 1,
                name = "Женя",
                surname = "Петрова",
                number = "+7 (903) 215 21 61"
            ),
            Contact(
                id = 2,
                name = "Ваня",
                surname = "Сидоров",
                number = "+7 (904) 326 23 35"
            ),
            Contact(
                id = 3,
                name = "Катя",
                surname = "Владимировна",
                number = "+7 (906) 200 02 42"
            )
        )
    }
}