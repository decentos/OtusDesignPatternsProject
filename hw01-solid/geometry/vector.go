package geometry

// Вектор.
type Vector struct {
	X, Y float64
}

func (v Vector) Add(ov Vector) Vector {
	return Vector{
		X: v.X + ov.X,
		Y: v.Y + ov.Y,
	}
}
