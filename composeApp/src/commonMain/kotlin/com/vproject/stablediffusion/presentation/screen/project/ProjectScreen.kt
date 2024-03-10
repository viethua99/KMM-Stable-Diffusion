package com.vproject.stablediffusion.presentation.screen.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.stablediffusion.SharedRes
import com.vproject.stablediffusion.model.TestSample
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.beforeafter.BeforeAfterImage
import com.vproject.stablediffusion.presentation.screen.generate.GenerateModel
import dev.icerock.moko.resources.compose.painterResource

object ProjectTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.Project)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Project",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel: ProjectModel = getScreenModel()

        val projectUiState by screenModel.state.collectAsState()
        val parentNavigator = LocalNavigator.current?.parent

        LaunchedEffect(Unit) {
            screenModel.getGeneratedImageList()
        }

        ProjectContent(
            projectUiState
        )
    }
}

@Composable
private fun ProjectContent(
    projectUiState: ProjectUiState,
    onMultipleDeleteClicked: () -> Unit = {}
) {
    Column {
        ProjectTopBar(
            onMultipleDeleteClicked = onMultipleDeleteClicked
        )

        when (projectUiState) {
            is ProjectUiState.Initial -> {
                // TODO
            }

            is ProjectUiState.Loading -> {

            }

            is ProjectUiState.Success -> {
                if (projectUiState.projectList.isEmpty()) {
                    Text("Your project list is empty")
                } else {
                    ProjectList(projectUiState)
                }
            }

            is ProjectUiState.Error -> {
                Text("Error")
            }
        }
    }
}

@Composable
private fun ProjectTopBar(
    modifier: Modifier = Modifier,
    onMultipleDeleteClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier.height(50.dp).fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Project",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
        )

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd).padding(horizontal = 10.dp),
            onClick = {}) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(SharedRes.images.ic_delete),
                contentDescription = "actionIconContentDescription",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun ProjectList(
    projectUiState: ProjectUiState.Success
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(projectUiState.projectList) { index, project ->
            ProjectItem(modifier = Modifier, project)
        }
    }
}

@Composable
private fun ProjectItem(
    modifier: Modifier = Modifier,
    textStyle: TestSample
) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable {},
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (textStyle is TestSample.TextToImageSample) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(textStyle.canvasPreset.aspectRatio)
                    .clip(RoundedCornerShape(15.dp)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                bitmap = textStyle.imageResource
            )
        } else if (textStyle is TestSample.ImageToImageSample) {
            BeforeAfterImage(
                enableZoom = false,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .aspectRatio(textStyle.canvasPreset.aspectRatio),
                beforeImage = textStyle.beforeImageResource,
                afterImage = textStyle.afterImageResource,
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = textStyle.prompt,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
