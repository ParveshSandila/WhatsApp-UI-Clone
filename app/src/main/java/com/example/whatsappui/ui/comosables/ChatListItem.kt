package com.example.whatsappui.ui.comosables

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.whatsappui.R
import com.example.whatsappui.ui.models.Chat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatListItem(
    chat:Chat,
    onItemClicked : () -> Unit,
    onItemLongPress : (_id:String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onItemClicked()
                },
                onLongClick = {
                    onItemLongPress(chat._id)
                }
            )
            .padding(
                10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .weight(0.15f)
                .aspectRatio(1f)
                .clip(
                    shape = CircleShape
                )
            ,
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(chat.userAvtar)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .fallback(R.drawable.ic_user)
                    .build(),
            ),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = chat.userName,
                maxLines = 1,
                style = TextStyle(
                    color = Color(0xFF353535),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = chat.lastMessage,
                maxLines =1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color(0xFFA7A7A7),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Column(
            modifier = Modifier
                .weight(0.2f)
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp
                ),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = chat.time.toString(),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = TextStyle(
                    color = if(chat.unreadMessagesCount > 0)
                                MaterialTheme.colors.primaryVariant
                            else
                                Color(0xFF353535),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            if(chat.unreadMessagesCount > 0){
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(
                            color = MaterialTheme.colors.primaryVariant,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = chat.unreadMessagesCount.toString(),
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            color = Color(0xFFFFFFFF),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}