import java.lang.Integer.parseInt

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.INVALID_NULLOREMPTY)
        }
        if (value?.length != 11) {
            return listOf(ErrorCode.INVALID_CHARACTERNUMBER)
        }
        if (!(value?.startsWith('7') || value?.startsWith('8'))) {
            return listOf(ErrorCode.INVALID_FIRSTCHARACTER)
        }
        if (value.contains("[\\D]+".toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER)
        }
        return emptyList()
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.INVALID_NULLOREMPTY)
        }
        if (value?.length > 16) {
            return listOf(ErrorCode.INVALID_CHARACTERNUMBER)
        }
        if (value.contains("[^А-Яа-я]+".toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER)
        }
        return emptyList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.INVALID_NULLOREMPTY)
        }
        if (value?.length > 32) {
            return listOf(ErrorCode.INVALID_CHARACTERNUMBER)
        }
        if (value.contains("[А-Яа-я]+".toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER)
        }
        if (!value.contains(("@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                    "(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])\$").toRegex())) {
            return listOf(ErrorCode.INVALID_DOMEINNAME)
        }
        return emptyList()
    }
}


class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) {
            return listOf(ErrorCode.INVALID_NULLOREMPTY)
        }
        if (value.length > 11) {
            return listOf(ErrorCode.INVALID_CHARACTERNUMBER)
        }
        if (value.contains("[\\D]+".toRegex())) {
            return listOf(ErrorCode.INVALID_CHARACTER)
        } else {
            var sum = 0;
            for (i in 0 until 8) {
                sum += value[i].toInt() * (9 - i)
            }
            var checkDigit:String? = "";
            if (sum < 100) {
                checkDigit = sum.toString();
            } else if (sum > 101) {
                checkDigit = (sum % 101).toString();
                if (sum === 100) {
                    checkDigit = "00";
                }
            }
            if (!checkDigit.equals(value[9].plus(value[10].toString()))) {
                return listOf(ErrorCode.INVALID_CHECKNUMBER)
            }
        }
        return emptyList()
    }
}

