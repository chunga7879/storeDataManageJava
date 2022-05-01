new Swiper('.kinds .swiper-container', {
    autoplay: {
        delay: 6000
    },
    loop: true,
    slidesPerView: 3,
    spaceBetween: 10,
    centeredSlides: true,
    pagination: {
        el: '.kinds .swiper-pagination',
        clickable: true
    },
    navigation: {
        prevEl: '.kinds .swiper-prev',
        nextEl: '.kinds .swiper-next',
    }
})





