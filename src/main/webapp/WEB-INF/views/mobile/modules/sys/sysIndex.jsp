<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="index_section">
	<header>
        <h1 class="title">${fns:getConfig('productName')}</h1>
        <nav class="right">
            <a data-icon="arrow-down-left-2" href="#" id="btnLogout">退出</a>
        </nav>
        <!-- Demo styles -->
        <style>
            .swiper-header {
                width: 100%;
                height: 40px;
                line-height: 40px;
                font-size: 14px;
                border-radius: 5px;
                padding: 0px 10px 0px 10px;
                background-color: lightgreen;
            }

            .swiper-container {
                width: 100%;
                margin: 5px auto;
            }

            .swiper-slide {
                text-align: center;
                font-size: 18px;
                background: #fff;

                /* Center slide text vertically */
                display: -webkit-box;
                display: -ms-flexbox;
                display: -webkit-flex;
                display: flex;
                -webkit-box-pack: center;
                -ms-flex-pack: center;
                -webkit-justify-content: center;
                justify-content: center;
                -webkit-box-align: center;
                -ms-flex-align: center;
                -webkit-align-items: center;
                align-items: center;
            }

            .swiper-container-movie {
                height: 200px;
            }

            .swiper-slide-movie {
                width: 150px !important;
                height: 200px;
            }

            .swiper-slide-movie img{
                width: 150px;
                height: 200px;
            }

        </style>
    </header>

    <article class="active" data-scroll="true" style="overflow:auto !important;">
        <div style="padding: 10px 0 20px;">
        <ul class="list inset demo-list">
            <li data-icon="next" data-selected="selected">
                <span class="icon user"></span>
                <a href="#user_section?test=abc" data-target="section">
                    <strong>视频测试</strong>
                </a>
            </li>
        </ul>
        </div>

        <!-- Swiper -->
        <div style="padding: 5px 10px 5px 10px;">
            <div id="adContainer" class="swiper-container" style="height: 200px;">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" style="height: 200px;">
                        <a href="#user_section?test=abc" data-target="section">
                            <img style="height: 200px;" src="${ctxUserFiles}/1/images/movie1.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#user_section?test=abc" data-target="section">
                            <img style="width: 100%;height: 300px;" src="${ctxUserFiles}/1/images/movie2.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#user_section?test=abc" data-target="section">
                            <img style="width: 100%;height: 300px;" src="${ctxUserFiles}/1/images/movie3.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#user_section?test=abc" data-target="section">
                            <img style="width: 100%;height: 300px;" src="${ctxUserFiles}/1/images/movie4.jpg" />
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <%-- Movie List Begin--%>
        <div style="padding: 5px 10px 5px 10px;">
            <div class="swiper-header">Catalog A</div>
            <div id="movieContainer1" class="swiper-container swiper-container-movie">
                <div class="swiper-wrapper">
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie1.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie2.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie3.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie4.jpg" />
                        </a>
                    </div>
                </div>
                <!-- Add Pagination -->
                <div id="moviePagination1" class="swiper-pagination"></div>

                <!-- 如果需要导航按钮 -->
                <div id="movieBtnPrev1" class="swiper-button-prev"></div>
                <div id="movieBtnNext1" class="swiper-button-next"></div>
            </div>
        </div>
        <div style="padding: 5px 10px 5px 10px;">
            <div class="swiper-header">Catalog B</div>
            <div id="movieContainer2" class="swiper-container swiper-container-movie">
                <div class="swiper-wrapper">
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie4.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie3.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie2.jpg" />
                        </a>
                    </div>
                    <div class="swiper-slide swiper-slide-movie">
                        <a href="#user_section?test=abc" data-target="section">
                            <img src="${ctxUserFiles}/1/images/movie1.jpg" />
                        </a>
                    </div>
                </div>
                <!-- Add Pagination -->
                <div id="moviePagination2" class="swiper-pagination"></div>

                <!-- 如果需要导航按钮 -->
                <div id="movieBtnPrev2" class="swiper-button-prev"></div>
                <div id="movieBtnNext2" class="swiper-button-next"></div>
            </div>
        </div>
        <%-- Movie List End--%>

    </article>

    <script type="text/javascript">
        $('#btnLogout').tap(function(){
   			J.confirm('确认提示','确认要退出吗？',function(){
   				$.get("${ctx}/logout", function(){
   					sessionid = '';
   					J.showToast('退出成功！', 'success');
   					J.Router.goTo('#login_section');
   				});
   			});
   		});

        $(document).ready(function () {
            var swiper = new Swiper('#adContainer', {
                spaceBetween: 30,
                centeredSlides: true,
                autoplay: 1000,
                autoplayDisableOnInteraction: false,
                direction: 'vertical'
            });

            var movieSwiper1 = new Swiper('#movieContainer1', {
                pagination: '#moviePagination1',
                loop: true,
                slidesPerView: 3,
                paginationClickable: true,
                nextButton: '#movieBtnNext1',
                prevButton: '#movieBtnPrev1',
                spaceBetween: 30,
                freeMode: true
            });
            var movieSwiper2 = new Swiper('#movieContainer2', {
                pagination: '#moviePagination2',
                loop: true,
                slidesPerView: 3,
                // 如果需要前进后退按钮
                nextButton: '#movieBtnNext2',
                prevButton: '#movieBtnPrev2',
                paginationClickable: true,
                spaceBetween: 30,
                freeMode: true
            });
        });

    </script>
</section>