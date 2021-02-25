package actions

import (
	"errors"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
)

var (
	ErrDirectionNil       = errors.New("direction is nil")
	ErrAngularVelocityNil = errors.New("angular velocity is nil")

	_ Doer = (*rotate)(nil)
)

//go:generate mockgen -source=$GOFILE -destination ./mocks/mock_rotable.go -package mocks Rotable
// Поворот.
type Rotable interface {
	// Направление.
	SetDirection(v *geometry.Vector)
	GetDirection() *geometry.Vector

	// Угловая скорость.
	GetAngularVelocity() *geometry.Vector
}

// Структура поворота.
type rotate struct {
	r Rotable
}

func NewRotate(r Rotable) Doer {
	return &rotate{r}
}

func (r *rotate) Do() error {
	d := r.r.GetDirection()
	if d == nil {
		return ErrDirectionNil
	}

	av := r.r.GetAngularVelocity()
	if av == nil {
		return ErrAngularVelocityNil
	}

	*d = d.Add(*av)
	r.r.SetDirection(d)

	return nil
}
