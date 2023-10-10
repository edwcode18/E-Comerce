import React from 'react'
<<<<<<< HEAD
import { Header,HeaderNav } from '../../share/layout/components/index.js';
import { SectionsHomeImg } from '../../share/layout/components/section/index.js';
=======
import { Header,HeaderNav } from '../../share/layout/components';
import { SectionsHomeImg } from '../../share/layout/components/section';
>>>>>>> origin/main

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
