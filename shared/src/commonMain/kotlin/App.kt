import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.Battery
import compose.icons.feathericons.Bell
import compose.icons.feathericons.Book
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Database
import compose.icons.feathericons.Heart
import compose.icons.feathericons.MapPin
import compose.icons.feathericons.Search
import compose.icons.feathericons.Star
import compose.icons.feathericons.Tag
import modal.BannerModal
import modal.CategoryModal
import modal.ProductModal
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        Column {
            Column(
                modifier = Modifier.weight(1.0f)
            ) {
                Header()
                Spacer(modifier = Modifier.height(24.dp))
                Banner()
                Category()
                Spacer(modifier = Modifier.height(24.dp))
                Products()
            }
            BottomTab()
        }

    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier.fillMaxWidth().height(120.dp).background(color303846).padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1.0f)
                        .background(color = colorffffff, shape = RoundedCornerShape(100))
                        .height(35.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 12.dp).fillMaxSize()
                    ) {
                        Icon(
                            imageVector = FeatherIcons.Search,
                            contentDescription = null,
                            tint = colorbdbdbd,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Search...", color = colorbdbdbd, fontSize = 16.sp)
                        Spacer(modifier = Modifier.weight(1.0f))
                        Icon(
                            imageVector = FeatherIcons.Camera,
                            contentDescription = null,
                            tint = colorbdbdbd,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = FeatherIcons.Bell,
                    contentDescription = null,
                    tint = colorbdbdbd,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Icon(
                    imageVector = FeatherIcons.MapPin,
                    contentDescription = null,
                    tint = colorbdbdbd,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Deliver to ", color = Color.White.copy(alpha = 0.8f))
                Text(text = "India", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun Banner() {
    val banners: List<BannerModal> = listOf(
        BannerModal(
            title = "Essentials for\ngames",
            image = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aGVhZHBob25lfGVufDB8fDB8fHww&w=1000&q=80",
            gradient = listOf(Color(0xfffbd07c), Color(0xfff7f779))
        ), BannerModal(
            title = "Daily\ngadgets",
            image = "https://t3.ftcdn.net/jpg/02/77/26/88/360_F_277268851_gf1sqxszssfMlPasgqvjtf2ddwMKmeLI.jpg",
            gradient = listOf(Color(0xfff6d5f7), Color(0xfffbe9d7))
        )
    )
    val pagerState = rememberPagerState(
    )
    BoxWithConstraints {
        HorizontalPager(
            pageCount = banners.size,
            state = pagerState,
            pageSize = PageSize.Fixed(maxWidth.times(.8f)),
            pageSpacing = 20.dp,
            contentPadding = PaddingValues(end = 20.dp, start = 20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(130.dp).clip(RoundedCornerShape(10.dp))
                    .background(brush = Brush.linearGradient(colors = banners[it].gradient))
                    .paint(
                        painter = rememberImagePainter(
                            url = banners[it].image,
                            errorPainter = { painterResource("compose-multiplatform.xml") }
                        ),
                        contentScale = ContentScale.Crop
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    banners[it].title,
                    color = Color(0xff000000),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

            }
        }
    }
}

@Composable
fun Category() {
    val categories = listOf(
        CategoryModal(
            title = "Today's\n Deals", bgColor = Color(0xFFffddb0), icon = FeatherIcons.Star
        ),
        CategoryModal(
            title = "Electronics", bgColor = Color(0xFFf8a6a6), icon = FeatherIcons.Camera
        ),
        CategoryModal(
            title = "Home & Kitchen", bgColor = Color(0xFFc7dfdb), icon = FeatherIcons.Database
        ),
        CategoryModal(title = "Toys", bgColor = Color(0xFFfac683), icon = FeatherIcons.Tag),
        CategoryModal(title = "Books", bgColor = Color(0xFFbdedc7), icon = FeatherIcons.Book),
        CategoryModal(
            title = "Medicines", bgColor = Color(0xFFfecb00), icon = FeatherIcons.Battery
        ),
    )

    LazyRow {
        items(categories.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(100.dp).padding(20.dp)
                        .background(categories[it].bgColor, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = categories[it].icon,
                        contentDescription = null,
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }
                Text(categories[it].title, color = Color(0xFF85888c), fontSize = 12.sp)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Products() {
    val products = listOf(
        ProductModal(
            productName = "New Lipstick Clinique",
            price = "₹798",
            image = "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/37252/lipstick-cosmetics-clipart-md.png",
            soldCount = 334,
            rating = 4.5f
        ),
        ProductModal(
            productName = "Nike Air Max 270",
            price = "₹1700",
            image = "https://freepngimg.com/download/shoes/28530-3-nike-shoes-transparent.png",
            soldCount = 898,
            rating = 4.0f
        ),
        ProductModal(
            productName = "Boat Headphone",
            price = "₹900",
            image = "https://pnghq.com/wp-content/uploads/pnghq.com-boat-headphones-png-2810-download.png",
            soldCount = 435,
            rating = 4.1f
        ),
        ProductModal(
            productName = "Rexy Dinosaur",
            price = "₹1110",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-zEIaVYShGkUffSwKQER7JBDCx_EeTnmtew&usqp=CAU",
            soldCount = 643,
            rating = 3.9f
        ),
    )
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
        ) {
            Text(
                "Featured Products",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "see all", color = Color(0xFFfebe6d), fontSize = 12.sp)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(products.size) {
                Card(
                    backgroundColor = Color.White,
                    elevation = 2.dp,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                            .aspectRatio(.70f),
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                products[it].image,
                                filterQuality = FilterQuality.High,
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp),
                            contentScale = ContentScale.FillHeight
                        )
                        Text(
                            products[it].productName,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            modifier = Modifier.weight(1.0f)
                        )
                        Text(
                            products[it].price,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "${products[it].soldCount} sold | ",
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color(0xFFffec46)
                            )
                            Spacer(modifier = Modifier.weight(1.0f))
                            Icon(
                                imageVector = FeatherIcons.Heart,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomTab() {
    Box(
        modifier = Modifier
            .shadow(AppBarDefaults.TopAppBarElevation)
            .zIndex(1f)
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.White)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                tint = Color(0xFFfebd69),
                modifier = Modifier.size(30.dp)
            )
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = null,
                tint = Color(0xFFCCCCCC),
                modifier = Modifier.size(30.dp)
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color(0xFFCCCCCC),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

expect fun getPlatformName(): String