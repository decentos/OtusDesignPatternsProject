package actions

import (
	"errors"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
)

var (
	ErrPositionNil = errors.New("position is nil")
	ErrVelocityNil = errors.New("velocity is nil")

	_ Doer = (*move)(nil)
)

//go:generate mockgen -source=$GOFILE -destination ./mocks/mock_movable.go -package mocks Movable
// Движение.
type Movable interface {
	// Позиция.
	SetPosition(v *geometry.Vector)
	GetPosition() *geometry.Vector

	// Скорость.
	GetVelocity() *geometry.Vector
}

// Структура движения.
type move struct {
	m Movable
}

func NewMove(m Movable) Doer {
	return &move{m}
}

func (m *move) Do() error {
	p := m.m.GetPosition()
	if p == nil {
		return ErrPositionNil
	}

	v := m.m.GetVelocity()
	if v == nil {
		return ErrVelocityNil
	}

	*p = p.Add(*v)
	m.m.SetPosition(p)

	return nil
}
