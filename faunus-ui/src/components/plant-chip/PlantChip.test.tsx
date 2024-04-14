/**
 * @jest-environment jsdom
 */

import React from 'react';
import { render, screen } from '@testing-library/react';
import {within} from '@testing-library/dom'
import PlantChip from './PlantChip';
import { Vazirmatn } from "next/font/google";
import '@testing-library/jest-dom';

jest.mock('next/font/google', () => ({
  Vazirmatn: jest.fn().mockReturnValue({ className: 'mocked-class' }), // Provide a mock implementation
}));

describe('PlantChip', () => {
    test('renders chip text', () => {
        const text = 'هوای خنک';
        render(<PlantChip/>);
        const chip = screen.getByTestId('chip-component');

        const textElement = within(chip).getByText(text);

        expect(textElement).toBeInTheDocument();
    });
});