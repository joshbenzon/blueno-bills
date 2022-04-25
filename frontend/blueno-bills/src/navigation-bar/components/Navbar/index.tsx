import React from 'react';
import {
    Nav,
    NavLink,
    Bars,
    NavMenu,
    NavBtn,
    NavBtnLink,
} from './navigation';

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavLink to='/'>
                    <img src={require('../../images/blueno-logo.png')} alt='logo' width='75px' />
                </NavLink>
                <Bars />
                <NavMenu>
                    <NavLink to='/account'>
                        Account
                    </NavLink>
                    <NavLink to='/menu'>
                        Menu
                    </NavLink>
                    <NavLink to='/request'>
                        Request
                    </NavLink>
                    <NavLink to='/transfer'>
                        Transfer
                    </NavLink>
                     {/*Second Nav */}
                     {/*<NavBtnLink to='/sign-in'>Sign In</NavBtnLink> */}
                </NavMenu>
                <NavBtn>
                    <NavBtnLink to='/signin'>Sign In</NavBtnLink>
                </NavBtn>
            </Nav>
        </>
    );
};

export default Navbar;
