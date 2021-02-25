package actions //nolint:dupl

import (
	"testing"

	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/actions/mocks"
	"github.com/decentos/OtusDesignPatternsProject/hw01-solid/geometry"
	"github.com/golang/mock/gomock"
	"github.com/stretchr/testify/require"
)

func TestNewRotate(t *testing.T) {
	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		rotable := mocks.NewMockRotable(ctrl)

		rotate := NewRotate(rotable)
		require.NotNil(t, rotate)
	})
}

func TestRotateDo(t *testing.T) {
	v1 := geometry.Vector{X: 1, Y: 2}
	v2 := geometry.Vector{X: 3, Y: 4}
	v3 := geometry.Vector{X: 4, Y: 6}

	t.Run("check normal state", func(t *testing.T) {
		ctrl := gomock.NewController(t)
		defer ctrl.Finish()

		rotable := mocks.NewMockRotable(ctrl)
		rotable.EXPECT().GetDirection().Return(&v1)
		rotable.EXPECT().GetAngularVelocity().Return(&v2)
		rotable.EXPECT().SetDirection(&v3).Return()

		err := NewRotate(rotable).Do()
		require.NoError(t, err)
	})
	t.Run("check errors", func(t *testing.T) {
		t.Run("direction is nil", func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			rotable := mocks.NewMockRotable(ctrl)
			rotable.EXPECT().GetDirection().Return(nil)

			err := NewRotate(rotable).Do()
			require.EqualError(t, ErrDirectionNil, err.Error())
		})
		t.Run("angular velocity is nil", func(t *testing.T) {
			ctrl := gomock.NewController(t)
			defer ctrl.Finish()

			rotable := mocks.NewMockRotable(ctrl)
			rotable.EXPECT().GetDirection().Return(&v1)
			rotable.EXPECT().GetAngularVelocity().Return(nil)

			err := NewRotate(rotable).Do()
			require.EqualError(t, ErrAngularVelocityNil, err.Error())
		})
	})
}
