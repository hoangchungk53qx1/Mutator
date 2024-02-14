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

package com.tunjid.mutator.demo.sections

import androidx.compose.runtime.Composable
import com.tunjid.mutator.demo.editor.CallToAction
import com.tunjid.mutator.demo.editor.CodeBlock
import com.tunjid.mutator.demo.editor.Markdown
import com.tunjid.mutator.demo.editor.SectionLayout
import com.tunjid.mutator.demo.snails.Snail7

@Composable
fun Section5() = SectionLayout {
    Markdown(oneMarkdown)
    CodeBlock(twoCode)
    Markdown(threeMarkdown)
    CodeBlock(fourCode)
    Snail7()
    CallToAction(Snail7Cta)
}

private val oneMarkdown = """
# Formalizing state production

The merge approach can be formalized into an extension function on the `CoroutineScope` the state is produced in:
""".trimIndent()

private val twoCode = """
fun <State: Any> CoroutineScope.stateFlowProducer(
    initialState: State,
    started: SharingStarted = SharingStarted.WhileSubscribed(),
    inputs: List<Flow<Mutation<State>>>
) : StateFlowProducer<State>
""".trimIndent()

private val threeMarkdown = """
Where the use of it in the snail example becomes:
""".trimIndent()

private val fourCode = """
class Snail7StateHolder(
    private val scope: CoroutineScope
) {

    private val speedChanges: Flow<Mutation<Snail7State>> = …

    private val progressChanges: Flow<Mutation<Snail7State>> = …

    private val stateProducer = scope.stateFlowProducer(
        initialState = Snail7State(),
        started = SharingStarted.WhileSubscribed(),
        inputs = listOf(
            speedChanges,
            progressChanges,
        )
    )

    val state: StateFlow<Snail7State> = stateProducer.state

    fun setSnailColor(index: Int) = stateProducer.launch {
        mutate { copy(color = colors[index]) }
    }

    fun setProgress(progress: Float) = stateProducer.launch {
        mutate { copy(progress = progress) }
    }
} 
""".trimIndent()

private val Snail7Cta = """
Snail7 is identical to Snail6; just with a formalized state production approach.    
""".trimIndent()
