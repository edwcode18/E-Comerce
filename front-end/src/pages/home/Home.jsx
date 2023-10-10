import React from 'react'
import { Header,HeaderNav } from '../../share/layout/components';
import { SectionsHomeImg } from '../../share/layout/components/section';

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
