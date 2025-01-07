package me.floow.login.uilogic

import android.net.Uri
import me.floow.uikit.util.state.ValidatedField
import me.floow.uikit.util.state.ValidatedField.Companion.initialField

interface CreateProfileState {
    data class Edit(
        val name: ValidatedField = initialField,
        val username: ValidatedField = initialField,
        val bio: ValidatedField = initialField,
        val avatarUri: Uri? = null,
    ) : CreateProfileState

    data object Uploading : CreateProfileState
}