/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tunjid.mutator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Mutator<Action : Any, State : Any> {
    val state: StateFlow<State>
    val accept: (Action) -> Unit
}

/**
 * Data class holding a change transform for a type [T].
 */
data class Mutation<T : Any>(
    val mutate: T.() -> T
)

fun <State: Any> Mutator<Mutation<State>, State>.accept(
    mutator: State.() -> State
) = accept(Mutation(mutator))
