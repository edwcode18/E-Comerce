import React from 'react'

export const HeaderNav = () => {
  return (
    <div className="container-fluid">
        <div className="row py-3">
          <div className="d-flex  justify-content-center justify-content-sm-between align-items-center">
            <nav className="main-menu d-flex navbar navbar-expand-lg">

              <button className="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
                <span className="navbar-toggler-icon"></span>
              </button>

              <div className="offcanvas offcanvas-end show"  id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel" aria-modal="true" role="dialog">

                <div className="offcanvas-header justify-content-center">
                  <button type="button" className="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>

                <div className="offcanvas-body">
              
                  <select className="filter-categories border-0 mb-0 me-5">
                    <option>Shop by Departments</option>
                    <option>Groceries</option>
                    <option>Drinks</option>
                    <option>Chocolates</option>
                  </select>
              
                  <ul className="navbar-nav justify-content-end menu-list list-unstyled d-flex gap-md-3 mb-0">
                    <li className="nav-item active">
                      <a href="#women" className="nav-link">Women</a>
                    </li>
                    <li className="nav-item dropdown">
                      <a href="#men" className="nav-link">Men</a>
                    </li>
                    <li className="nav-item">
                      <a href="#kids" className="nav-link">Kids</a>
                    </li>
                    <li className="nav-item">
                      <a href="#accessories" className="nav-link">Accessories</a>
                    </li>
                    <li className="nav-item dropdown">
                      <a className="nav-link dropdown-toggle" role="button" id="pages" data-bs-toggle="dropdown" aria-expanded="false">Pages</a>
                      <ul className="dropdown-menu" aria-labelledby="pages">
                        <li><a href="about.html" className="dropdown-item">About Us</a></li>
                        <li><a href="shop.html" className="dropdown-item">Shop<span className="badge bg-success text-dark ms-2">PRO</span></a></li>
                        <li><a href="single-product.html" className="dropdown-item">Single Product<span className="badge bg-success text-dark ms-2">PRO</span></a></li>
                        <li><a href="cart.html" className="dropdown-item">Cart<span className="badge bg-success text-dark ms-2">PRO</span></a></li>
                        <li><a href="checkout.html" className="dropdown-item">Checkout<span className="badge bg-success text-dark ms-2">PRO</span></a></li>
                        <li><a href="blog.html" className="dropdown-item">Blog</a></li>
                        <li><a href="single-post.html" className="dropdown-item">Single Post</a></li>
                        <li><a href="styles.html" className="dropdown-item">Styles</a></li>
                        <li><a href="contact.html" className="dropdown-item">Contact<span className="badge bg-success text-dark ms-2">PRO</span></a></li>
                        <li><a href="thank-you.html" className="dropdown-item">Thank You</a></li>
                      </ul>
                    </li>
                    <li className="nav-item">
                      <a href="#brand" className="nav-link">Brand</a>
                    </li>
                    <li className="nav-item">
                      <a href="#sale" className="nav-link">Sale</a>
                    </li>
                    <li className="nav-item">
                      <a href="#blog" className="nav-link">Blog</a>
                    </li>
                  </ul>
                
                </div>

              </div>

            <div className="offcanvas-backdrop fade show"></div></nav>
            <div className="d-none d-lg-block">
              <a href="#" className="nav-link btn-coupon-code">
                {/* <img src="images/gift.svg"/> */}
                <strong className="ms-2">Get your coupon code</strong>
              </a>
            </div>
          </div>
        </div>
      </div>
  )
}
