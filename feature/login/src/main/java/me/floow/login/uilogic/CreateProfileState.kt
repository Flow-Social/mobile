package me.floow.login.uilogic

import me.floow.uikit.util.state.ValidatedField
import me.floow.uikit.util.state.ValidatedField.Companion.initialField

interface CreateProfileState {
    data class Edit(
        val name: ValidatedField = initialField,
        val username: ValidatedField = initialField,
        val bio: ValidatedField = initialField
    ) : CreateProfileState

    data object Uploading : CreateProfileState
}