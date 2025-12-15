# Graphing Calculator

A Java-based graphing calculator application that allows users to visualize mathematical functions and their derivatives in real-time.

## Features

- **Function Visualization**: Graph polynomial, trigonometric, logarithmic, and other mathematical functions
- **Derivative Plotting**: Automatically calculates and displays the derivative of your function
- **Interactive Controls**:
  - Zoom in/out functionality
  - Adjustable domain and range
  - Custom zoom factor (x50.0)
  - Click to plot points and view coordinates
  - Reset view to default settings
- **Multiple Function Types Supported**:
  - Polynomial functions
  - Trigonometric functions (Sine, Cosine, Tangent)
  - Logarithmic functions
  - Custom composite functions

## Project Structure

```
src/
└── main/
    └── java/
        └── csci2020u.lab09/
            ├── components/
            │   ├── functions/
            │   │   ├── Cosine
            │   │   ├── Function
            │   │   ├── Logarithmic
            │   │   ├── Polynomial
            │   │   ├── Sine
            │   │   ├── Tangent
            │   │   └── Trignometric
            │   ├── CartesianPlane
            │   ├── GraphComponent
            │   ├── GraphPanel
            │   └── Point
            ├── enums/
            │   ├── FunctionType
            │   └── RootType
            ├── listeners/
            │   ├── DomainRangeListener
            │   ├── DrawListener
            │   ├── PointClickListener
            │   ├── QuitListener
            │   ├── ResetListener
            │   └── ZoomListener
            └── GraphGUI
```

## Usage

### Running the Application

1. Compile the Java project
2. Run the `GraphGUI` main class
3. The graphing calculator window will appear

### Entering Functions

Enter mathematical functions in the input field at the top using standard notation:
- Use `x` as the variable
- Example: `x^5-3x^4-5x^3+7x^2+3x-2`
- The application will automatically plot both the function (orange) and its derivative (purple)

<img width="1003" height="687" alt="image" src="https://github.com/user-attachments/assets/c742253f-f507-4013-beb6-39e05b682f25" />

### Controls

- **ZOOM IN/ZOOM OUT**: Adjust the viewing window scale
- **x50.0**: Set custom zoom factor
- **DOMAIN**: Adjust the x-axis range (default: -400.0 to 400.0)
- **RANGE**: Adjust the y-axis range (default: -400.0 to 400.0)
- **RESET**: Return to default view settings
- **QUIT**: Exit the application
- **Click on Graph**: View coordinates of clicked points (displayed as "CP: (x, y)")

### Example Functions

The calculator can handle various function types:
- `x^n` (Polynomial)
- `sin(x)` (Trigonometric)
- `cos(x)` (Trigonometric)
- `log(x)` (Logarithmic)
- And combinations thereof

## Technical Details

### Components

- **Function Classes**: Implement different mathematical function types with evaluation and derivative calculation
- **CartesianPlane**: Manages the coordinate system and grid display
- **GraphComponent**: Handles the rendering of functions on the plane
- **GraphPanel**: Main container for the graphing area
- **Listeners**: Handle user interactions (zooming, clicking, resetting, etc.)

### Features Implementation

- Real-time function plotting
- Automatic derivative calculation and visualization
- Interactive point selection with coordinate display
- Dynamic domain and range adjustment
- Smooth zoom functionality

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Swing library (included in standard JDK)

## Screenshot

The application displays:
- A coordinate plane with gridlines
- Orange curve representing the function f(x)
- Purple curve representing the derivative f'(x)
- Interactive control panel on the right
- Function input at the top
- Critical point display at clicked locations

<img width="994" height="690" alt="image" src="https://github.com/user-attachments/assets/cdb3af26-7d5f-44a9-b565-caf00a409289" />

## Acknowledgment

Inspired by Desmos graphing calculator functionality.
Originally developed as a lab assignment for CSCI 2020U: Software Systems Development and Integration. This personal copy has been cleaned up and refactored for portfolio purposes
