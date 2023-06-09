import React from 'react'
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';

// import 'swiper/swiper.scss';

import logo1 from '../../../../../public/images/banner-image-1.jpg';
import logo2 from '../../../../../public/images/banner-image-2.jpg';

// Instala los módulos Swiper necesarios
SwiperCore.use([Navigation, Pagination, Autoplay]);

export const SectionsHomeImg = () => {
return (
<div className="py-3"
    >
        {/* // style="background-image: url('images/background-pattern.jpg');background-repeat: no-repeat;background-size: cover;" */}
    <div className="container-fluid">
        <div className="row">
            <div className="col-md-12">

                <div className="">

                    <div className="banner-ad large bg-info block-1">

                    <Swiper
                        navigation={{ nextEl: '.swiper-button-next' }} disabled
                        pagination={{ clickable: true }}
                        autoplay={{ delay: 3000 }}
                        loop
                        >

                        <SwiperSlide>
                            <img src={logo1} alt="Imagen 1" />
                        </SwiperSlide>
                        <SwiperSlide>
                            <img src={logo2} alt="Imagen 2" />
                        </SwiperSlide>

                    </Swiper>

                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
)
}
