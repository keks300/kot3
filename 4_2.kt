fun main() {
// Функция main - точка входа в программу. Отвечает за взаимодействие с пользователем.

    // Выводит пользователю меню выбора действия.
    println("Выберите действие:")
    println("1. Зашифровать текст")
    println("2. Расшифровать текст")

    // Считывает выбор пользователя и преобразует в целое число.
    // Если ввод не является числом, возвращает null.
    val choice = readLine()?.toIntOrNull()

    // Обработка выбора пользователя с использованием конструкции when.
    when (choice) {
        1 -> {
            // Ветвь для шифрования текста.

            // Выводит приглашение ввести текст для шифрования.
            print("Введите текст для шифрования: ")
            // Считывает введенный текст и преобразует в верхний регистр.
            val text = readLine()?.toUpperCase()

            // Выводит приглашение ввести ключевое слово.
            print("Введите ключевое слово: ")
            // Считывает введенное ключевое слово и преобразует в верхний регистр.
            val key = readLine()?.toUpperCase()

            // Проверка наличия ввода текста и ключа.
            if (text != null && key != null) {
                // Вызывает функцию vigenereEncrypt для шифрования текста и выводит результат.
                val encryptedText = vigenereEncrypt(text, key)
                println("Зашифрованный текст: $encryptedText")
            } else {
                // Выводит сообщение об ошибке при некорректном вводе.
                println("Неверный ввод.")
            }
        }
        2 -> {
            // Ветвь для расшифрования текста.

            // Выводит приглашение ввести текст для расшифрования.
            print("Введите текст для расшифрования: ")
            // Считывает введенный текст и преобразует в верхний регистр.
            val textToDecrypt = readLine()?.toUpperCase()

            // Выводит приглашение ввести ключевое слово.
            print("Введите ключевое слово: ")
            // Считывает введенное ключевое слово и преобразует в верхний регистр.
            val decryptionKey = readLine()?.toUpperCase()

            // Проверка наличия ввода текста и ключа.
            if (textToDecrypt != null && decryptionKey != null) {
                // Вызывает функцию vigenereDecrypt для расшифрования текста и выводит результат.
                val decryptedText = vigenereDecrypt(textToDecrypt, decryptionKey)
                println("Расшифрованный текст: $decryptedText")
            } else {
                // Выводит сообщение об ошибке при некорректном вводе.
                println("Неверный ввод.")
            }
        }
        else -> {
            // Ветвь для некорректного выбора пользователя.

            // Выводит сообщение об ошибке при некорректном выборе.
            println("Неверный выбор. Программа завершена.")
        }
    }
}

// Функция для шифрования текста шифром Виженера.
fun vigenereEncrypt(text: String, key: String): String {
    // Вызов общей функции vigenereProcess для шифрования текста с русским алфавитом и 33 буквами.
    return vigenereProcess(text, key, true, russianAlphabet, 33)
}

// Функция для расшифрования текста шифром Виженера.
fun vigenereDecrypt(text: String, key: String): String {
    // Вызов общей функции vigenereProcess для расшифрования текста с русским алфавитом и 33 буквами.
    return vigenereProcess(text, key, false, russianAlphabet, 33)
}

// Общая функция для процесса шифрования/расшифрования шифром Виженера.
fun vigenereProcess(text: String, key: String, encrypt: Boolean, alphabet: List<Char>, alphabetSize: Int): String {
    // Инициализация строки для хранения результата.
    val result = StringBuilder()

    // Определение длины ключа.
    val keyLength = key.length

    // Проход по каждому символу в тексте.
    for (i in text.indices) {
        val char = text[i]

        // Проверка, является ли символ заглавной буквой.
        if (char in alphabet) {
            // Преобразование символа в индекс относительно начала алфавита. indexOf метод встроенного класса String. Он возвращает индекс первого вхождения указанного символа или подстроки в строке.
            val textIndex = alphabet.indexOf(char)

            // Получение символа ключа для текущего положения.
            val keyChar = key[i % keyLength]
            // Преобразование символа ключа в индекс относительно начала алфавита.
            val keyIndex = alphabet.indexOf(keyChar)

            // Вычисление нового индекса для символа текста.
            val newIndex = if (encrypt) (textIndex + keyIndex) % alphabetSize
            else (textIndex - keyIndex + alphabetSize) % alphabetSize

            // Преобразование нового индекса обратно в символ и добавление к результату.
            result.append(alphabet[newIndex])
        } else {
            // Если символ не входит в алфавит, добавление его к результату без изменений.
            result.append(char)
        }
    }

    // Возврат результата в виде строки.
    return result.toString()
}

// Русский алфавит для шифра Виженера.
val russianAlphabet = listOf(
    'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О',
    'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
)
