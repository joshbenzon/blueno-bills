import React from "react";

interface Props {
    border: string;
    color: string;
    children?: React.ReactNode;
    height: string;
    onClick: () => void;
    radius: string
    width: string;
}

const Button: React.FC<Props> = ({
                                     border,
                                     color,
                                     height,
                                     width,
                                     radius,
                                     children,
                                     onClick
                                 }) => {

    return (
        <button
            style={{
                backgroundColor: color,
                border,
                borderRadius: radius,
                height,
                width
            }}
            onClick={onClick}
        >
            {children}
        </button>
    );
}

export default Button;
