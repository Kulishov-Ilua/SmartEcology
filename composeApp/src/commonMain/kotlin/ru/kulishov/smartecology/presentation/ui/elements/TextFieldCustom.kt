package ru.kulishov.smartecology.presentation.ui.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import smartecology.composeapp.generated.resources.Res
import smartecology.composeapp.generated.resources.info
import smartecology.composeapp.generated.resources.trash

@Composable
fun TextFieldCustom(
    text: String,
    onTextChange: (String) -> Unit,
    readOnly: Boolean,
    placeholder: String
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        readOnly = readOnly,
        placeholder = { Text(placeholder, style = MaterialTheme.typography.bodyMedium) },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.trash),
                tint = Color(79, 79, 79),
                contentDescription = "info"
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldColors(
            focusedTextColor = Color(79, 79, 79),
            unfocusedTextColor = Color(79, 79, 79),
            disabledTextColor = Color(79, 79, 79),
            errorTextColor = Color.Red,
            focusedContainerColor = Color(242, 242, 242),
            unfocusedContainerColor = Color(242, 242, 242),
            disabledContainerColor = Color(242, 242, 242),
            errorContainerColor = Color(242, 242, 242),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorCursorColor = MaterialTheme.colorScheme.onSurface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = Color(79, 79, 79),
            unfocusedLeadingIconColor = Color(79, 79, 79),
            disabledLeadingIconColor = Color(79, 79, 79),
            errorLeadingIconColor = Color(79, 79, 79),
            focusedTrailingIconColor = Color.Transparent,
            unfocusedTrailingIconColor = Color(79, 79, 79),
            disabledTrailingIconColor = Color(79, 79, 79),
            errorTrailingIconColor = Color(79, 79, 79),
            focusedLabelColor = Color.Transparent,
            unfocusedLabelColor = Color(79, 79, 79),
            disabledLabelColor = Color(79, 79, 79),
            errorLabelColor = Color(79, 79, 79),
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
            unfocusedPlaceholderColor = Color(79, 79, 79),
            disabledPlaceholderColor = Color(79, 79, 79),
            errorPlaceholderColor = Color(79, 79, 79),
            focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedSupportingTextColor = Color(79, 79, 79),
            disabledSupportingTextColor = Color(79, 79, 79),
            errorSupportingTextColor = Color(79, 79, 79),
            focusedPrefixColor = MaterialTheme.colorScheme.onSurface,
            unfocusedPrefixColor = Color(79, 79, 79),
            disabledPrefixColor = Color(79, 79, 79),
            errorPrefixColor = Color(79, 79, 79),
            focusedSuffixColor = MaterialTheme.colorScheme.onSurface,
            unfocusedSuffixColor = Color(79, 79, 79),
            disabledSuffixColor = Color(79, 79, 79),
            errorSuffixColor = Color(79, 79, 79),
            textSelectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        ),
        modifier = Modifier.fillMaxWidth()
            .border(width = 5.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
    )
}