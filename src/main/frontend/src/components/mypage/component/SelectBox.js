import { useState, useEffect } from 'react';

import styled from 'styled-components';

const SelectWrapper = styled.div`
	border-radius: var(--br-8xs);
	background-color: var(--color-white);
	border: 1px solid var(--color-lightgray-100);
	box-sizing: border-box;
	width: 420px;
	height: 42px;
	display: flex;
	align-items: center;

    &:focus-within {
        border: 1px solid;
    }
`;

const StyledSelect = styled.select`
	font-size: var(--font-size-sm);
	font-weight: 300;
	color: 1px solid;
	width: 100%;
	height: 100%;
	border-width: 0px;
	appearance: none;
	padding-left: 12px;

    &:focus {
        outline: none;
    }
`;

const StyledArrow = styled.div`
    position: absolute;
    right: 12px;
    top: 50%;
    transform: translateY(-50%);
    pointer-events: none;

    &::after {
        content: '';
        border-left: 6px solid transparent;
        border-right: 6px solid transparent;
        border-top: 6px solid var(--color-lightslategray);
        display: block;
    }
`;

function SelectBox({ selectWrapperStyle, options, value, onChange }) {
	return (
	    <SelectWrapper
	        className={selectWrapperStyle}
        >
            <StyledSelect
                value={value}
                onChange={onChange}
            >
                {options.map((option) => (
                    <option
                        key={option.value}
                        value={option.value}
                    >
                        {option.name}
                    </option>
                ))}
            </StyledSelect>
            <StyledArrow />
		</SelectWrapper>
	);
};

export default SelectBox;
