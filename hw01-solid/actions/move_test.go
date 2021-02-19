package actions //nolint:dupl

import (
	"testing"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/actions/mocks"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
	"github.com/golang/mock/gomock"
	"github.com/stretchr/testify/require"
)

func TestNewMove(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		movable := mocks.NewMockMovable(ctrl)

		move := NewMove(movable)
		require.NotNil(t, move)
	})
}

func TestMoveDo(t *testing.T) {
	v1 := geometry.Vector{X: 1, Y: 2}
	v2 := geometry.Vector{X: 3, Y: 4}
	v3 := geometry.Vector{X: 4, Y: 6}

	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		movable := mocks.NewMockMovable(ctrl)
		movable.EXPECT().GetPosition().Return(&v1)
		movable.EXPECT().GetVelocity().Return(&v2)
		movable.EXPECT().SetPosition(&v3).Return()

		err := NewMove(movable).Do()
		require.NoError(t, err)
	})
	t.Run("check errors", func(t *testing.T) {
		t.Run("position is nil", func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			movable := mocks.NewMockMovable(ctrl)
			movable.EXPECT().GetPosition().Return(nil)

			err := NewMove(movable).Do()
			require.EqualError(t, ErrPositionNil, err.Error())
		})
		t.Run("velocity is nil", func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			movable := mocks.NewMockMovable(ctrl)
			movable.EXPECT().GetPosition().Return(&v1)
			movable.EXPECT().GetVelocity().Return(nil)

			err := NewMove(movable).Do()
			require.EqualError(t, ErrVelocityNil, err.Error())
		})
	})
}
