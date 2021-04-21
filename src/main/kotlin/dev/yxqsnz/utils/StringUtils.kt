package dev.yxqsnz.utils

import java.text.CharacterIterator
import java.text.StringCharacterIterator
import kotlin.math.abs


/**
 * Convert bytes to human-readable String.
 */
fun humanizeBytes(bytes: Long): String {
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)

    if (absB < 1024) return "$bytes B"

    var value = absB
    val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
    var i = 40
    while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
        value = value shr 10
        ci.next()
        i -= 10
    }
    value *= java.lang.Long.signum(bytes).toLong()

    return String.format("%.1f %ciB", value / 1024.0, ci.current())
}

