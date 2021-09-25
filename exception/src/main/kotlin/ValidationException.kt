class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_CHARACTERNUMBER(200, "Недопустимое количество символов"),
    INVALID_FIRSTCHARACTER(300, "Номер телефона должен начинаться с цифры 7 или 8"),
    INVALID_DOMEINNAME(400, "Недопустимое имя домена"),
    INVALID_CHECKNUMBER(500, "Недопустимое контрольное число"),
    INVALID_NULLOREMPTY(600, "Значение не должно быть пустым или null")


    // ...
}