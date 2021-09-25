import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error - INVALID_CHARACTERNUMBER`() {
        val client = getClientFromJson("/fail/phone/user_with_short_phonenumber.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое количество символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTERNUMBER)
    }

    @Test
    fun `fail save client - phone validation error - INVALID_CHARACTER`() {
        val client = getClientFromJson("/fail/phone/user_with_nondigital_phonenumber.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - phone validation error - INVALID_NULLOREMPTY`() {
        val client = getClientFromJson("/fail/phone/user_with_null_phone.json")
        val exception = assertFailsWith<ValidationException>("Значение не должно быть пустым или null") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULLOREMPTY)
    }

    @Test
    fun `fail save client - first name validation error - INVALID_NULLOREMPTY`() {
        val client = getClientFromJson("/fail/name/user_with_null_firstname.json")
        val exception = assertFailsWith<ValidationException>("Значение не должно быть пустым или null") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULLOREMPTY)
    }

    @Test
    fun `fail save client - first name validation error - INVALID_CHARACTERNUMBER`() {
        val client = getClientFromJson("/fail/name/user_with_long_firstname.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое количество символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTERNUMBER)
    }

    @Test
    fun `fail save client - first name validation error - INVALID_CHARACTER`() {
        val client = getClientFromJson("/fail/name/user_with_noncyrillic_firstname.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }


    @Test
    fun `fail save client - last name validation error - INVALID_NULLOREMPTY`() {
        val client = getClientFromJson("/fail/name/user_with_null_lastname.json")
        val exception = assertFailsWith<ValidationException>("Значение не должно быть пустым или null") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULLOREMPTY)
    }

    @Test
    fun `fail save client - last name validation error - INVALID_CHARACTERNUMBER`() {
        val client = getClientFromJson("/fail/name/user_with_long_lastname.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое количество символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTERNUMBER)
    }

    @Test
    fun `fail save client - last name validation error - INVALID_CHARACTER`() {
        val client = getClientFromJson("/fail/name/user_with_noncyrillic_lastname.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - email validation error - INVALID_NULLOREMPTY`() {
        val client = getClientFromJson("/fail/email/user_with_null_email.json")
        val exception = assertFailsWith<ValidationException>("Значение не должно быть пустым или null") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULLOREMPTY)
    }

    @Test
    fun `fail save client - email validation error - INVALID_CHARACTERNUMBER`() {
        val client = getClientFromJson("/fail/email/user_with_long_email.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое количество символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTERNUMBER)
    }

    @Test
    fun `fail save client - email validation error - INVALID_CHARACTER`() {
        val client = getClientFromJson("/fail/email/user_with_cyrillic_email.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }


    @Test
    fun `fail save client - email validation error - INVALID_DOMEINNAME`() {
        val client = getClientFromJson("/fail/email/user_with_wrong_domainname.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое имя домена") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_DOMEINNAME)
    }

    @Test
    fun `fail save client - snils validation error - INVALID_NULLOREMPTY`() {
        val client = getClientFromJson("/fail/snils/user_with_null_snils.json")
        val exception = assertFailsWith<ValidationException>("Значение не должно быть пустым или null") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULLOREMPTY)
    }

    @Test
    fun `fail save client - snils validation error - INVALID_CHARACTERNUMBER`() {
        val client = getClientFromJson("/fail/snils/user_with_invalid_snilsnumber.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое количество символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTERNUMBER)
    }


    @Test
    fun `fail save client - snils validation error - INVALID_CHECKNUMBER`() {
        val client = getClientFromJson("/fail/snils/user_with_invalid_checknumber.json")
        val exception = assertFailsWith<ValidationException>("Недопустимое контрольное число") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHECKNUMBER)
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}