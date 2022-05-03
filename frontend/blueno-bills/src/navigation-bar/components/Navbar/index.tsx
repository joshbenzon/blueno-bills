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
                    <img src={require('../../images/blueno-logo.png')} alt='logo' width='75px'/>
                </NavLink>

                <Bars/>
                <NavMenu>
                    <NavLink to='/request'>
                        Request
                    </NavLink>
                    <NavLink to='/transfer'>
                        Transfer
                    </NavLink>
                    <NavLink to='/menu'>
                        Menus
                    </NavLink>
                    <NavLink to='/account'>
                        Account
                    </NavLink>
                    <NavLink to='/settings'>
                        Settings
                    </NavLink>
                </NavMenu>

                <NavBtn>
                    <NavBtnLink to='/signin'>Sign In</NavBtnLink>
                </NavBtn>

            </Nav>
        </>
    );
};

export default Navbar;
