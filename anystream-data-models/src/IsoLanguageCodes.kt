/**
 * AnyStream
 * Copyright (C) 2021 Drew Carlson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package anystream.models

internal data class LangCode(
    val name: String,
    val nativeNative: String,
    val iso639_1: String,
    val iso639_2T: String,
    val iso639_2B: String,
)

internal object IsoLanguageCodes {

    fun by639_2(code: String): LangCode? {
        val lowercaseCode = code.lowercase()
        return by639_2T[lowercaseCode] ?: by639_2B[lowercaseCode]
    }

    fun by639_2T(code: String): LangCode? {
        return by639_2T[code.lowercase()]
    }

    fun by639_2B(code: String): LangCode? {
        return by639_2B[code.lowercase()]
    }

    private val codes: List<LangCode> = listOf(
        LangCode("Serbian", "српски језик", "sr", "srp", "srp"),
        LangCode("Romanian, Moldavian, Moldovan", "Română", "ro", "ron", "rum"),
        LangCode("Sichuan Yi, Nuosu", "ꆈꌠ꒿ Nuosuhxop", "ii", "iii", "iii"),
        LangCode("Tahitian", "Reo Tahiti", "ty", "tah", "tah"),
        LangCode("Tagalog", "Wikang Tagalog", "tl", "tgl", "tgl"),
        LangCode("Yiddish", "ייִדיש", "yi", "yid", "yid"),
        LangCode("Akan", "Akan", "ak", "aka", "aka"),
        LangCode("Malay", "Bahasa Melayu, بهاس ملايو‎", "ms", "msa", "may"),
        LangCode("Arabic", "العربية", "ar", "ara", "ara"),
        LangCode("Norwegian", "Norsk", "no", "nor", "nor"),
        LangCode("Ojibwa", "ᐊᓂᔑᓈᐯᒧᐎᓐ", "oj", "oji", "oji"),
        LangCode("Fulah", "Fulfulde, Pulaar, Pular", "ff", "ful", "ful"),
        LangCode("Persian", "فارسی", "fa", "fas", "per"),
        LangCode("Albanian", "Shqip", "sq", "sqi", "alb"),
        LangCode("Aymara", "aymar aru", "ay", "aym", "aym"),
        LangCode("Azerbaijani", "azərbaycan dili", "az", "aze", "aze"),
        LangCode("Chinese", "中文 (Zhōngwén), 汉语, 漢語", "zh", "zho", "chi"),
        LangCode("Cree", "ᓀᐦᐃᔭᐍᐏᐣ", "cr", "cre", "cre"),
        LangCode("Estonian", "eesti, eesti keel", "et", "est", "est"),
        LangCode("Guaraní", "Avañe'ẽ", "gn", "grn", "grn"),
        LangCode("Inupiaq", "Iñupiaq, Iñupiatun", "ik", "ipk", "ipk"),
        LangCode("Inuktitut", "ᐃᓄᒃᑎᑐᑦ", "iu", "iku", "iku"),
        LangCode("Kanuri", "Kanuri", "kr", "kau", "kau"),
        LangCode("Komi", "коми кыв", "kv", "kom", "kom"),
        LangCode("Kongo", "Kikongo", "kg", "kon", "kon"),
        LangCode("Kurdish", "Kurdî, کوردی‎", "ku", "kur", "kur"),
        LangCode("Latvian", "latviešu valoda", "lv", "lav", "lav"),
        LangCode("Malagasy", "fiteny malagasy", "mg", "mlg", "mlg"),
        LangCode("Mongolian", "Монгол хэл", "mn", "mon", "mon"),
        LangCode("Oromo", "Afaan Oromoo", "om", "orm", "orm"),
        LangCode("Pashto, Pushto", "پښتو", "ps", "pus", "pus"),
        LangCode("Quechua", "Runa Simi, Kichwa", "qu", "que", "que"),
        LangCode("Sardinian", "sardu", "sc", "srd", "srd"),
        LangCode("Swahili", "Kiswahili", "sw", "swa", "swa"),
        LangCode("Uzbek", "Oʻzbek, Ўзбек, أۇزبېك‎", "uz", "uzb", "uzb"),
        LangCode("Zhuang, Chuang", "Saɯ cueŋƅ, Saw cuengh", "za", "zha", "zha"),
        LangCode("Bislama", "Bislama", "bi", "bis", "bis"),
        LangCode("Norwegian Bokmål", "Norsk Bokmål", "nb", "nob", "nob"),
        LangCode("Norwegian Nynorsk", "Norsk Nynorsk", "nn", "nno", "nno"),
        LangCode("Indonesian", "Bahasa Indonesia", "id", "ind", "ind"),
        LangCode("Twi", "Twi", "tw", "twi", "twi"),
        LangCode("Esperanto", "Esperanto", "eo", "epo", "epo"),
        LangCode("Interlingua", "Interlingua", "ia", "ina", "ina"),
        LangCode("Interlingue", "Originally called Occidental; then Interlingue after WWII", "ie", "ile", "ile"),
        LangCode("Ido", "Ido", "io", "ido", "ido"),
        LangCode("Volapük", "Volapük", "vo", "vol", "vol"),
        LangCode("Bihari languages", "भोजपुरी", "bh", "bih", "bih"),
        LangCode("Hebrew (modern)", "עברית", "he", "heb", "heb"),
        LangCode("Sanskrit", "संस्कृतम्", "sa", "san", "san"),
        LangCode("Church Slavic, Church Slavonic, Old Church Slavonic, Old Slavonic, Old Bulgarian", "ѩзыкъ словѣньскъ", "cu", "chu", "chu"),
        LangCode("Pali", "पाऴि", "pi", "pli", "pli"),
        LangCode("Avestan", "avesta", "ae", "ave", "ave"),
        LangCode("Latin", "latine, lingua latina", "la", "lat", "lat"),
        LangCode("Armenian", "Հայերեն", "hy", "hye", "arm"),
        LangCode("Swati", "SiSwati", "ss", "ssw", "ssw"),
        LangCode("Tibetan", "བོད་ཡིག", "bo", "bod", "tib"),
        LangCode("South Ndebele", "isiNdebele", "nr", "nbl", "nbl"),
        LangCode("Slovene", "Slovenski Jezik, Slovenščina", "sl", "slv", "slv"),
        LangCode("Oriya", "ଓଡ଼ିଆ", "or", "ori", "ori"),
        LangCode("North Ndebele", "isiNdebele", "nd", "nde", "nde"),
        LangCode("Nauru", "Dorerin Naoero", "na", "nau", "nau"),
        LangCode("Maori", "te reo Māori", "mi", "mri", "mao"),
        LangCode("Marathi", "मराठी", "mr", "mar", "mar"),
        LangCode("Luba-Katanga", "Kiluba", "lu", "lub", "lub"),
        LangCode("Rundi", "Ikirundi", "rn", "run", "run"),
        LangCode("Central Khmer", "ខ្មែរ, ខេមរភាសា, ភាសាខ្មែរ", "km", "khm", "khm"),
        LangCode("Western Frisian", "Frysk", "fy", "fry", "fry"),
        LangCode("Bengali", "বাংলা", "bn", "ben", "ben"),
        LangCode("Avaric", "авар мацӀ, магӀарул мацӀ", "av", "ava", "ava"),
        LangCode("Abkhazian", "аҧсуа бызшәа, аҧсшәа", "ab", "abk", "abk"),
        LangCode("Afar", "Afaraf", "aa", "aar", "aar"),
        LangCode("Afrikaans", "Afrikaans", "af", "afr", "afr"),
        LangCode("Amharic", "አማርኛ", "am", "amh", "amh"),
        LangCode("Aragonese", "aragonés", "an", "arg", "arg"),
        LangCode("Assamese", "অসমীয়া", "as", "asm", "asm"),
        LangCode("Bambara", "bamanankan", "bm", "bam", "bam"),
        LangCode("Bashkir", "башҡорт теле", "ba", "bak", "bak"),
        LangCode("Basque", "euskara, euskera", "eu", "eus", "baq"),
        LangCode("Belarusian", "беларуская мова", "be", "bel", "bel"),
        LangCode("Bosnian", "bosanski jezik", "bs", "bos", "bos"),
        LangCode("Breton", "brezhoneg", "br", "bre", "bre"),
        LangCode("Bulgarian", "български език", "bg", "bul", "bul"),
        LangCode("Burmese", "ဗမာစာ", "my", "mya", "bur"),
        LangCode("Catalan, Valencian", "català, valencià", "ca", "cat", "cat"),
        LangCode("Chamorro", "Chamoru", "ch", "cha", "cha"),
        LangCode("Chechen", "нохчийн мотт", "ce", "che", "che"),
        LangCode("Chichewa, Chewa, Nyanja", "chiCheŵa, chinyanja", "ny", "nya", "nya"),
        LangCode("Chuvash", "чӑваш чӗлхи", "cv", "chv", "chv"),
        LangCode("Cornish", "Kernewek", "kw", "cor", "cor"),
        LangCode("Corsican", "corsu, lingua corsa", "co", "cos", "cos"),
        LangCode("Croatian", "hrvatski jezik", "hr", "hrv", "hrv"),
        LangCode("Czech", "čeština, český jazyk", "cs", "ces", "cze"),
        LangCode("Danish", "dansk", "da", "dan", "dan"),
        LangCode("Divehi, Dhivehi, Maldivian", "ދިވެހި", "dv", "div", "div"),
        LangCode("Dutch, Flemish", "Nederlands, Vlaams", "nl", "nld", "dut"),
        LangCode("Dzongkha", "རྫོང་ཁ", "dz", "dzo", "dzo"),
        LangCode("English", "English", "en", "eng", "eng"),
        LangCode("Ewe", "Eʋegbe", "ee", "ewe", "ewe"),
        LangCode("Faroese", "føroyskt", "fo", "fao", "fao"),
        LangCode("Fijian", "vosa Vakaviti", "fj", "fij", "fij"),
        LangCode("Finnish", "suomi, suomen kieli", "fi", "fin", "fin"),
        LangCode("French", "français, langue française", "fr", "fra", "fre"),
        LangCode("Galician", "Galego", "gl", "glg", "glg"),
        LangCode("Georgian", "ქართული", "ka", "kat", "geo"),
        LangCode("German", "Deutsch", "de", "deu", "ger"),
        LangCode("Greek (modern)", "ελληνικά", "el", "ell", "gre"),
        LangCode("Gujarati", "ગુજરાતી", "gu", "guj", "guj"),
        LangCode("Haitian, Haitian Creole", "Kreyòl ayisyen", "ht", "hat", "hat"),
        LangCode("Hausa", "(Hausa) هَوُسَ", "ha", "hau", "hau"),
        LangCode("Herero", "Otjiherero", "hz", "her", "her"),
        LangCode("Hindi", "हिन्दी, हिंदी", "hi", "hin", "hin"),
        LangCode("Hiri Motu", "Hiri Motu", "ho", "hmo", "hmo"),
        LangCode("Hungarian", "magyar", "hu", "hun", "hun"),
        LangCode("Irish", "Gaeilge", "ga", "gle", "gle"),
        LangCode("Igbo", "Asụsụ Igbo", "ig", "ibo", "ibo"),
        LangCode("Icelandic", "Íslenska", "is", "isl", "ice"),
        LangCode("Italian", "Italiano", "it", "ita", "ita"),
        LangCode("Japanese", "日本語 (にほんご)", "ja", "jpn", "jpn"),
        LangCode("Javanese", "ꦧꦱꦗꦮ, Basa Jawa", "jv", "jav", "jav"),
        LangCode("Kalaallisut, Greenlandic", "kalaallisut, kalaallit oqaasii", "kl", "kal", "kal"),
        LangCode("Kannada", "ಕನ್ನಡ", "kn", "kan", "kan"),
        LangCode("Kashmiri", "कश्मीरी, كشميري‎", "ks", "kas", "kas"),
        LangCode("Kazakh", "қазақ тілі", "kk", "kaz", "kaz"),
        LangCode("Kikuyu, Gikuyu", "Gĩkũyũ", "ki", "kik", "kik"),
        LangCode("Kinyarwanda", "Ikinyarwanda", "rw", "kin", "kin"),
        LangCode("Kirghiz, Kyrgyz", "Кыргызча, Кыргыз тили", "ky", "kir", "kir"),
        LangCode("Korean", "한국어", "ko", "kor", "kor"),
        LangCode("Kuanyama, Kwanyama", "Kuanyama", "kj", "kua", "kua"),
        LangCode("Luxembourgish, Letzeburgesch", "Lëtzebuergesch", "lb", "ltz", "ltz"),
        LangCode("Ganda", "Luganda", "lg", "lug", "lug"),
        LangCode("Limburgan, Limburger, Limburgish", "Limburgs", "li", "lim", "lim"),
        LangCode("Lingala", "Lingála", "ln", "lin", "lin"),
        LangCode("Lao", "ພາສາລາວ", "lo", "lao", "lao"),
        LangCode("Lithuanian", "lietuvių kalba", "lt", "lit", "lit"),
        LangCode("Manx", "Gaelg, Gailck", "gv", "glv", "glv"),
        LangCode("Macedonian", "македонски јазик", "mk", "mkd", "mac"),
        LangCode("Malayalam", "മലയാളം", "ml", "mal", "mal"),
        LangCode("Maltese", "Malti", "mt", "mlt", "mlt"),
        LangCode("Marshallese", "Kajin M̧ajeļ", "mh", "mah", "mah"),
        LangCode("Navajo, Navaho", "Diné bizaad", "nv", "nav", "nav"),
        LangCode("Nepali", "नेपाली", "ne", "nep", "nep"),
        LangCode("Ndonga", "Owambo", "ng", "ndo", "ndo"),
        LangCode("Occitan", "occitan, lenga d'òc", "oc", "oci", "oci"),
        LangCode("Ossetian, Ossetic", "ирон æвзаг", "os", "oss", "oss"),
        LangCode("Panjabi, Punjabi", "ਪੰਜਾਬੀ", "pa", "pan", "pan"),
        LangCode("Polish", "język polski, polszczyzna", "pl", "pol", "pol"),
        LangCode("Portuguese", "Português", "pt", "por", "por"),
        LangCode("Romansh", "Rumantsch Grischun", "rm", "roh", "roh"),
        LangCode("Russian", "русский", "ru", "rus", "rus"),
        LangCode("Sindhi", "सिन्धी, سنڌي، سندھی‎", "sd", "snd", "snd"),
        LangCode("Northern Sami", "Davvisámegiella", "se", "sme", "sme"),
        LangCode("Samoan", "gagana fa'a Samoa", "sm", "smo", "smo"),
        LangCode("Sango", "yângâ tî sängö", "sg", "sag", "sag"),
        LangCode("Gaelic, Scottish Gaelic", "Gàidhlig", "gd", "gla", "gla"),
        LangCode("Shona", "chiShona", "sn", "sna", "sna"),
        LangCode("Sinhala, Sinhalese", "සිංහල", "si", "sin", "sin"),
        LangCode("Slovak", "Slovenčina, Slovenský Jazyk", "sk", "slk", "slo"),
        LangCode("Somali", "Soomaaliga, af Soomaali", "so", "som", "som"),
        LangCode("Southern Sotho", "Sesotho", "st", "sot", "sot"),
        LangCode("Spanish, Castilian", "Español", "es", "spa", "spa"),
        LangCode("Sundanese", "Basa Sunda", "su", "sun", "sun"),
        LangCode("Swedish", "Svenska", "sv", "swe", "swe"),
        LangCode("Tamil", "தமிழ்", "ta", "tam", "tam"),
        LangCode("Telugu", "తెలుగు", "te", "tel", "tel"),
        LangCode("Tajik", "тоҷикӣ, toçikī, تاجیکی‎", "tg", "tgk", "tgk"),
        LangCode("Thai", "ไทย", "th", "tha", "tha"),
        LangCode("Tigrinya", "ትግርኛ", "ti", "tir", "tir"),
        LangCode("Turkmen", "Türkmen, Түркмен", "tk", "tuk", "tuk"),
        LangCode("Tswana", "Setswana", "tn", "tsn", "tsn"),
        LangCode("Tongan (Tonga Islands)", "Faka Tonga", "to", "ton", "ton"),
        LangCode("Turkish", "Türkçe", "tr", "tur", "tur"),
        LangCode("Tsonga", "Xitsonga", "ts", "tso", "tso"),
        LangCode("Tatar", "татар теле, tatar tele", "tt", "tat", "tat"),
        LangCode("Uighur, Uyghur", "ئۇيغۇرچە‎, Uyghurche", "ug", "uig", "uig"),
        LangCode("Ukrainian", "Українська", "uk", "ukr", "ukr"),
        LangCode("Urdu", "اردو", "ur", "urd", "urd"),
        LangCode("Venda", "Tshivenḓa", "ve", "ven", "ven"),
        LangCode("Vietnamese", "Tiếng Việt", "vi", "vie", "vie"),
        LangCode("Walloon", "Walon", "wa", "wln", "wln"),
        LangCode("Welsh", "Cymraeg", "cy", "cym", "wel"),
        LangCode("Wolof", "Wollof", "wo", "wol", "wol"),
        LangCode("Xhosa", "isiXhosa", "xh", "xho", "xho"),
        LangCode("Yoruba", "Yorùbá", "yo", "yor", "yor"),
        LangCode("Zulu", "isiZulu", "zu", "zul", "zul")
    )

    private val by639_2T = codes.associateBy(LangCode::iso639_2T)
    private val by639_2B = codes.associateBy(LangCode::iso639_2B)
}
