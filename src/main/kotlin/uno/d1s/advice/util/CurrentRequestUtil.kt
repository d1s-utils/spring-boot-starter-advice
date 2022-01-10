/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and other contributors.
 */

package uno.d1s.advice.util

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

val currentRequest get() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request