import React from 'react'
import { Routes , Route} from 'react-router-dom'

import { Login } from '../pages/auth/login'
import { Home } from '../pages/home/home'
import { Register } from '../pages/auth/register'

export const AppRouter = () => {
  return (
    <>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    </>
  )
}
