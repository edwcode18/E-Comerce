import React from 'react'
import { Header,HeaderNav } from '../../share/layout/components/index.js';
import { SectionsHomeImg } from '../../share/layout/components/section/index.js';

export const Home = () => {
  return (
    <>
        <div className='container-fluid'>
            <Header/>
            {/* <HeaderNav/> */}
            <SectionsHomeImg/>
        </div>
    </>
  )
}
